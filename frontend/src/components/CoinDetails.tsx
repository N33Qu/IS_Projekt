import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getCoinById } from '../api/coinDataApi';
import { Card, ListGroup } from 'react-bootstrap';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';

const CoinDetails: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const [coin, setCoin] = useState<any>(null);

    useEffect(() => {
        const fetchCoin = async () => {
            try {
                const data = await getCoinById(id);
                setCoin(data);
            } catch (error) {
                console.error('Error fetching coin details:', error);
                // Handle the error, e.g., display an error message to the user
            }
        };

        fetchCoin();
    }, [id]);

    if (!coin) {
        return <div>Loading...</div>;
    }

    const chartData = coin.historicalPrices.map((price: any) => ({
        timestamp: price.timestamp,
        price: price.price,
    }));

    const groupedEvents: { [date: string]: any[] } = coin.historicalPrices.reduce(
        (acc: { [date: string]: any[] }, price: any) => {
            const date = new Date(price.timestamp).toISOString().split('T')[0];
            if (!acc[date]) {
                acc[date] = [];
            }
            const uniqueEvents = price.events.filter(
                (event: any) => !acc[date].some((e: any) => e.id === event.id)
            );
            acc[date].push(...uniqueEvents);
            return acc;
        },
        {}
    );

    return (
        <div className="container mt-4">
            <Card>
                <Card.Body>
                    <Card.Title>Coin Details</Card.Title>
                    <Card.Text>ID: {coin.id}</Card.Text>
                    <Card.Text>Name: {coin.name}</Card.Text>
                </Card.Body>
            </Card>
            <h3 className="mt-4">Historical Price Chart</h3>
            <ResponsiveContainer width="100%" height={400}>
                <LineChart data={chartData}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="timestamp" format={"MM/dd"}/>
                    <YAxis />
                    <Tooltip />
                    <Line type="monotone" dataKey="price" stroke="#8884d8" />
                </LineChart>
            </ResponsiveContainer>
            <h3 className="mt-4">Events</h3>
            {Object.entries(groupedEvents).map(([date, events]) => (
                <Card key={date} className="mb-3">
                    <Card.Header>{date}</Card.Header>
                    <ListGroup variant="flush">
                        {events.map((event: any) => (
                            <ListGroup.Item key={event.id}>
                                <Card.Title>{event.name}</Card.Title>
                                <Card.Text>Description: {event.description}</Card.Text>
                                <Card.Text>Published At: {event.publishedAt}</Card.Text>
                                {/* Add more event details as needed */}
                            </ListGroup.Item>
                        ))}
                    </ListGroup>
                </Card>
            ))}
        </div>
    );
};

export default CoinDetails;