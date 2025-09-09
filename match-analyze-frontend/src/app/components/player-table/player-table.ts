import { Component, Input } from '@angular/core';
import { Player, PlayerStats } from '../../models/models';
import { CommonModule } from '@angular/common';
import { Api } from '../../services/api'; // make sure your service is named correctly

@Component({
  selector: 'app-player-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './player-table.html',
  styleUrls: ['./player-table.css']
})
export class PlayerTable {
  @Input() players: Player[] = [];

  selectedPlayer: Player | null = null;
  selectedStats: PlayerStats | null = null;

  constructor(private apiService: Api) {}

  selectPlayer(player: Player) {
    if (this.selectedPlayer?.id === player.id) {
      this.selectedPlayer = null;
      this.selectedStats = null;
      return;
    }

    this.selectedPlayer = player;

    this.apiService.getPlayerStats(player.id).subscribe(stats => {
      this.selectedStats = stats;
    });
  }

  getRating(): string {
    return this.selectedStats?.rating?.toString() ?? '-';
  }
}
