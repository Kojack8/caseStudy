import React from 'react';
import './App.css';
import Navigation from './navigation/index.js';
import Router from './router/index.js';
import TestModule from "./testModule";
import 'bootstrap/dist/css/bootstrap.min.css';

const App = () => (
  <div className='app'>
      <TestModule />
    <h1>Video Production Rental Service</h1>

    <Navigation />
    <Router />
  </div>
);












export default App;