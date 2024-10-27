import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-opt1',
  standalone: true,
  imports: [],
  templateUrl: './opt1.component.html',
  styleUrls: ['./opt1.component.css']
})
export class Opt1Component {
  head = 'By Coordinates';
  alt: number | null = null;  // Property to hold altitude
  lat: number | null = null;  // Property to hold latitude
  result: string = ''; // Property to hold the result

  constructor(private http: HttpClient) {}

  // Method to handle form submission
  onSubmit() {
    const data = {
      altitude: this.alt,
      latitude: this.lat,
    };

    // Replace with your Java backend URL
    const apiUrl = 'http://localhost:8080/api/submit'; 

    this.http.post(apiUrl, data).subscribe({
      next: (response) => {
        console.log('Response from server:', response);
        this.result = 'Data sent successfully!'; // Optional: Set a success message
      },
      error: (err) => {
        console.error('Error sending data:', err);
        this.result = 'Error sending data'; // Optional: Set an error message
      }
    });
  }
}
