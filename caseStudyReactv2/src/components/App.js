import React, {useState} from 'react';
import './App.css';
import Navigation from './navigation/index.js';
import Router from './router/index.js';
import TestModule from "./testModule";
import 'bootstrap/dist/css/bootstrap.min.css';
//import axios from "axios";
//import Cookies from "js-cookie";

//const csrfToken = Cookies.get('XSRF-TOKEN');

const App = () => {

    const [username, setUsername] = useState("");

   /* useEffect(() => {
        axios({
            method: 'GET',
            url: "user/name",
            headers: {
                Authorization: 'Basic ' + window.btoa('caseStudyUser:Hamster5Lobster9Lightbulb'),
                'X-XSRF-TOKEN': csrfToken
            }
        }).then((response) => {
            console.log(response);
            setUsername(response.data)
        }).catch(err => {console.log(err)});
    }, [])
*/
    const userCallBackData = (data) => {

        setUsername(data)

    }

    return (
        <div className='app'>
            {username}
            <TestModule/>
            <h1>Video Production Rental Service</h1>

            <Navigation username={username}/>
            <Router callBack={userCallBackData} />
        </div>
    );

}











export default App;