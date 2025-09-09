import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Event } from '../../models/models';

@Component({
  selector: 'app-event-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './event-list.html',
  styleUrls: ['./event-list.css']
})
export class EventsList {
  @Input() events: Event[] | null = [];
  @Input() homeTeam: string = ''; 

  getEventIcon(type: string): string {
    switch (type.toLowerCase()) {
      case 'goal': return 'fas fa-futbol';      
      case 'shot': return 'fas fa-bullseye';     
      case 'tackle': return 'fas fa-shield-alt'; 
      default: return 'fas fa-star';             
    }
  }
}
