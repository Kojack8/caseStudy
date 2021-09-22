import React, {useState} from 'react';
import './App.css';
import Navigation from './navigation/index.js';
import Router from './router/index.js';
import TestModule from "./testModule";
import 'bootstrap/dist/css/bootstrap.min.css';

const App = () => {

    const [username, setUsername] = useState("");

    const userCallBackData = (data) => {
        setUsername(data);
    }

    return (
        <div className='app'>
            <TestModule/>
            <h1>Video Production Rental Service</h1>

            <Navigation username={username}/>
            <Router callBack={userCallBackData}/>
        </div>
    );

}











export default App;