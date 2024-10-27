export interface Main {
    temp: number;  // Temperature in degrees Celsius
    humidity: number;  // Humidity percentage
}

export interface Sys {
    country: string;  // Country code
}

export interface Location {
    name: string;  // Name of the city
    main: Main;  // Main weather information
    sys: Sys;  // System information
}
