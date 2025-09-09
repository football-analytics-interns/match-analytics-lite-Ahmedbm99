import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Event } from '../../models/models';

@Component({
  selector: 'app-event-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './event-list.html',
  styleUrl: './event-list.css'
})
export class EventsList {
  @Input() events: Event[] | null = [];
  @Input() homeTeam: string = ''; 

getEventIcon(type: string): string {
  switch (type.toLowerCase()) {
    case 'goal': return 'fas fa-futbol';       // football icon
    case 'shot': return 'fas fa-bullseye';     // target icon
    case 'tackle': return 'fas fa-shield-alt'; // shield icon
    default: return 'fas fa-star';
  }
}

}

