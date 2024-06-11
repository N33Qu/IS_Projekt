import React, { useEffect, useState } from 'react';
import { getAllEvents } from '../api/coinDataApi';
import { ListGroup, Form, Button } from 'react-bootstrap';

interface LoginData {
    username: string;
    password: string;
}

interface LoginFormProps {
    onLogin: (data: LoginData) => void;
}

const LoginForm: React.FC<LoginFormProps> = ({ onLogin }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            onLogin({ username, password });
        } catch (error) {
            console.error('Login failed:', error);

        }
    };

    return (
        <Form onSubmit={handleSubmit}>
            <Form.Group controlId="username">
                <Form.Label>Username</Form.Label>
                <Form.Control
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />
            </Form.Group>
            <Form.Group controlId="password">
                <Form.Label>Password</Form.Label>
                <Form.Control
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
            </Form.Group>
            <Button variant="primary" type="submit">
                Log In
            </Button>
        </Form>
    );
};

const EventList: React.FC = () => {
    const [events, setEvents] = useState([]);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    useEffect(() => {
        const fetchEvents = async () => {
            try {
                const data = await getAllEvents(username, password);
                setEvents(data);
            } catch (error) {
                console.error('Error fetching events:', error);
                // Handle the error, e.g., display an error message to the user
            }
        };

        if (isLoggedIn) {
            fetchEvents();
        }
    }, [isLoggedIn, username, password]);

    const handleLogin = (data: LoginData) => {
        setIsLoggedIn(true);
        setUsername(data.username);
        setPassword(data.password);
    };

    return (
        <div className="container mt-4">
            {!isLoggedIn ? (
                <LoginForm onLogin={handleLogin} />
            ) : (
                <>
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
                </>
            )}
        </div>
    );
};

export default EventList;