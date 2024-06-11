import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

export const getAllCoins = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/coins`);
        return response.data;
    } catch (error) {
        console.error('Error fetching coins:', error);
        throw error;
    }
};

export const getCoinById = async (id: string | undefined) => {
    const response = await axios.get(`${API_BASE_URL}/coins/${id}`);
    return response.data;
};



export const getAllEvents = async (username: string, password: string) => {
    const credentials = btoa(`${username}:${password}`);
    const config = {
        headers: {
            'Authorization': `Basic ${credentials}`
        }
    };

    try {
        const response = await axios.get(`${API_BASE_URL}/events/all`, config);
        return response.data;
    } catch (error) {
        console.error('Login failed:', error);
        throw error;
    }
};