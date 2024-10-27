import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Opt1Component } from './opt1/opt1.component';
import { Opt2Component } from './opt2/opt2.component';
import { Opt3Component } from './opt3/opt3.component';
import { Location } from './location'; // Importa tu interfaz 

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HttpClientModule, RouterOutlet, CommonModule, FormsModule, 
    Opt1Component, Opt2Component, Opt3Component],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Webther Checker';
  
  selectedTab: string = 'Opt1';
  location: string = 'Location';
  dat: string | unknown;  
  test: Location | null = null; // Propiedad para almacenar la respuesta  

  private http = inject(HttpClient);

  ngOnInit(): void {
    
  }

  getWeatherData() {
    const apiUrl = 'http://localhost:8080/api/data';
    this.http.get(apiUrl, { responseType: 'text' }).subscribe({
      next: (response) => {
        this.dat = response;
        console.log('Weather data received:', response);
      },
      error: (err) => {
        console.error('Error fetching weather data:', err);
      }
    });
  }

  getLocationData() {
    const apiUrl = 'http://localhost:8080/api/testing'; // URL de tu API
    this.http.get<Location>(apiUrl).subscribe({
      next: (response) => {
        this.test = response; // Asigna la respuesta a la propiedad
        console.log('Location data received:', response);
      },
      error: (err) => {
        console.error('Error fetching location data:', err);
      }
    });
  }

  selectOption(option: string) {
    this.selectedTab = option;
  }

  selectLocation(location: string){
    this.location = location;
  }
}
