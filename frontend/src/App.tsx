import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import { Navbar, Nav } from 'react-bootstrap';
import CoinList from './components/CoinList';
import CoinDetails from './components/CoinDetails';
import EventList from './components/EventList';
import 'bootstrap/dist/css/bootstrap.min.css';

const App: React.FC = () => {
  return (
      <Router>
        <div>
          <Navbar bg="light" expand="lg">
            <Navbar.Brand as={Link} to="/">Crypto App</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="mr-auto">
                <Nav.Link as={Link} to="/">Coins</Nav.Link>
                <Nav.Link as={Link} to="/events">Events</Nav.Link>
              </Nav>
            </Navbar.Collapse>
          </Navbar>

          <Routes>
            <Route path="/" element={<CoinList />} />
            <Route path="/coins/:id" element={<CoinDetails />} />
            <Route path="/events" element={<EventList />} />
          </Routes>
        </div>
      </Router>
  );
};

export default App;