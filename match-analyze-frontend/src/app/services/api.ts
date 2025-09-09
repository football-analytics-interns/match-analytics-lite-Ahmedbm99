import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Match, Event, PlayerStats } from '../models/models';
@Injectable({
  providedIn: 'root'
})
export class Api {
    private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getMatch(): Observable<Match> {
    return this.http.get<Match>(`${this.baseUrl}/match`);
  }

  addEvent(event: Event): Observable<Event> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Event>(`${this.baseUrl}/event`, event, { headers });
  }

  getPlayerStats(playerId: number): Observable<PlayerStats> {
    return this.http.get<PlayerStats>(`${this.baseUrl}/player/${playerId}`);
  }
}
