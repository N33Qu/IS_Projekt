import React, { useEffect, useState } from 'react';
import { getAllEvents } from '../api/coinDataApi';
import { ListGroup } from 'react-bootstrap';

const EventList: React.FC = () => {
    const [events, setEvents] = useState([]);

    useEffect(() => {
        const fetchEvents = async () => {
            try {
                const data = await getAllEvents();
                setEvents(data);
            } catch (error) {
                console.error('Error fetching events:', error);
                // Handle the error, e.g., display an error message to the user
            }
        };

        fetchEvents();
    }, []);

    return (
        <div className="container mt-4">
            <h2>Event List</h2>
            <ListGroup>
                {events.map((event: any) => (
                    <ListGroup.Item key={event.id}>
                        <p>Name: {event.id}</p>
                        <p>Description: {event.description}</p>
                        <p>Content: {event.content}</p>
                        <p>Published At: {event.publishedAt}</p>
                    </ListGroup.Item>
                ))}
            </ListGroup>
        </div>
    );
};

export default EventList;