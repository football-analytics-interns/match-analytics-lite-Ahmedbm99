import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Api } from '../../services/api';
import { Match, Player, Event } from '../../models/models';
import { PlayerTable } from '../player-table/player-table';
import { EventsList } from '../event-list/event-list';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule, PlayerTable, EventsList],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class Dashboard implements OnInit {
  match: Match | null = null;
  loading = true;
  toastMessage = '';
  showToast = false;

  newEvent: Partial<Event> = { type: 'GOAL', minute: 0, playerId: 0, assist: undefined, meta: undefined };

  constructor(private api: Api) {}

  ngOnInit(): void {
    this.api.getMatch().subscribe({
      next: (data) => { this.match = data; this.loading = false; },
      error: () => { this.loading = false; this.showToastMessage('Error loading match'); }
    });
  }

  public get homePlayers(): Player[] {
    return this.match?.players.filter(p => p.team === this.match?.homeTeam) ?? [];
  }

  public get awayPlayers(): Player[] {
    return this.match?.players.filter(p => p.team === this.match?.awayTeam) ?? [];
  }

  public get allPlayers(): Player[] {
    return [...this.homePlayers, ...this.awayPlayers];
  }

  public onAddEvent(): void {
    if (!this.match) return;

    if (!this.newEvent.playerId || !this.newEvent.minute || this.newEvent.minute < 1 || this.newEvent.minute > 90) {
      this.showToastMessage(' Fill all required fields correctly.');
      return;
    }

    const eventToSave: Partial<Event> = {
      type: this.newEvent.type!,
      minute: this.newEvent.minute!,
      playerId: this.newEvent.playerId!,
      matchId: this.match.id,
      assist: this.newEvent.assist,
      meta: this.newEvent.meta ? { onTarget: this.newEvent.meta } : { assistId: this.newEvent.assist?.id }
    };

    this.api.addEvent(eventToSave as Event).subscribe({
      next: saved => {
        this.match!.events.push(saved);
        this.resetForm();
        this.showToastMessage(' Event added successfully!');
      },
      error: err => {
        console.error(err);
        this.showToastMessage(' Error adding event.');
      }
    });
  }

  public resetForm(): void {
    this.newEvent = { type: 'goal', minute: 0, playerId: 0, assist: undefined, meta: undefined };
  }

  private showToastMessage(message: string): void {
    this.toastMessage = message;
    this.showToast = true;
    setTimeout(() => this.showToast = false, 3000);
  }
}
