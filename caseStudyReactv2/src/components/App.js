import React, {useEffect, useState} from 'react';
import './App.css';
import Navigation from './navigation/index.js';
import Router from './router/index.js';
import TestModule from "./testModule";
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import Cookies from "js-cookie";

const csrfToken = Cookies.get('XSRF-TOKEN');

const App = () => {

    const [username, setUsername] = useState("");
    const [auth, setAuth] = useState([]);

    useEffect(() => {
        axios({
            method: 'GET',
            url: "user/auth",
            headers: {
                'X-XSRF-TOKEN': csrfToken
            }
        }).then((response) => {
            setAuth(response.data)
        }).catch(err => {
            console.log(err)
        });
    }, [])

    const userCallBackData = (data) => {
        setUsername(data)
    }

    return (
        <div className='app'>
            <Navigation username={username}/>
            <Router callBack={userCallBackData}
                    auth={auth}/>
        </div>
    );
}

export default App;