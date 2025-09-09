import { Component, OnInit } from '@angular/core';
import { Api } from '../../services/api';
import { Match } from '../../models/models';
import { PlayerTable } from '../player-table/player-table';
import { EventsList } from '../event-list/event-list';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, PlayerTable, EventsList],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {
  match: Match | null = null;
  loading = true;

  constructor(private apiService: Api) {}

  ngOnInit(): void {
    this.apiService.getMatch().subscribe({
      next: (data) => {
        this.match = data;
        this.loading = false;
      },
      error: () => {
        this.loading = false;
        alert('Error loading match data');
      }
    });
  }
}
