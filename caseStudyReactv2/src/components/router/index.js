import {Route, Switch} from "react-router-dom";
import React, {useEffect, useState} from "react";
import Home from "../home";
import Inventory from "../inventory";
import Login from "../login"
import CreateUser from "../createUser";
import Logout from "../logout"
import axios from "axios";
import Cookies from "js-cookie";
import ShoppingCart from "../shoppingCart";

const csrfToken = Cookies.get('XSRF-TOKEN');

const Router = (props) => {

    const [username, setUsername] = useState("");

    useEffect(() => {
        axios({
            method: 'GET',
            url: "user/name",
            headers: {
                Authorization: 'Basic ' + window.btoa('caseStudyUser:Hamster5Lobster9Lightbulb'),
                'X-XSRF-TOKEN': csrfToken
            }
        }).then((response) => {
            setUsername(response.data)
        }).catch(err => {console.log(err)});
    }, [])

    const loginCallBackData = (data) => {
        setUsername(data);
    }

    const logoutCallBackData = (data) => {
        setUsername(data);
    }

    useEffect(() => {
        goBack();
    })

    const goBack = () => {
        props.callBack(username);
    }






    return (
        <Switch>
            <Route exact path='/' component={Home}/>
            <Route exact path='/inventory' component={Inventory}/>

            <Route exact path='/login'>
                <Login callBack={loginCallBackData}/>
            </Route>
            <Route exact path='/signup' component={CreateUser}/>
            <Route exact path='/logout'>
                <Logout username={username} callBack={logoutCallBackData} />
            </Route>
            <Route exact path="/cart" component={ShoppingCart}/>

        </Switch>
    );

}

export default Router;