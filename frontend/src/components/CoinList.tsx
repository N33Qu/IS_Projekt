import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getAllCoins } from '../api/coinDataApi';
import { ListGroup } from 'react-bootstrap';

const CoinList: React.FC = () => {
    const [coins, setCoins] = useState([]);

    useEffect(() => {
        const fetchCoins = async () => {
            try {
                const data = await getAllCoins();
                setCoins(data);
            } catch (error) {
                console.error('Error fetching coins:', error);
                // Handle the error, e.g., display an error message to the user
            }
        };

        fetchCoins();
    }, []);

    return (
        <div className="container mt-4">
            <h2>Coin List</h2>
            <ListGroup>
                {coins.map((coin: any) => (
                    <ListGroup.Item key={coin.id}>
                        <Link to={`/coins/${coin.id}`}>{coin.name}</Link>
                    </ListGroup.Item>
                ))}
            </ListGroup>
        </div>
    );
};

export default CoinList;