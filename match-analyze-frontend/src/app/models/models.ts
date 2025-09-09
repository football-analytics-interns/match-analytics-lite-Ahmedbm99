export interface Match {
  id: number;
  homeTeam: string;
  awayTeam: string;
  date: Date;
  players: Player[];
  events: Event[];
}

export interface Player {
  id: number;
  name: string;
  position?: string;
  team: string;
}

export interface Event {
  id: number;
  matchId: number;
  type: string;     
  minute: number;
  playerId: number;
  player?: Player;   
  assist?: Player;   
  meta?: Record<string, any>; 
}

export interface PlayerStats {
  playerId: number;
  goals: number;
  assists: number;
  rating: number;
}
