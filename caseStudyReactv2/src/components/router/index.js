import {Route, Switch} from "react-router-dom";
import React, {useEffect, useState} from "react";
import Home from "../home";
import Inventory from "../inventory";
import Login from "../login"
import CreateUser from "../createUser";
import Logout from "../logout"


const Router = (props) => {

    const [username, setUsername] = useState("");

    const userCallBackData = (data) => {
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
                <Login callBack={userCallBackData}/>
            </Route>
            <Route exact path='/signup' component={CreateUser}/>
            <Route exact path='/logout' component={Logout}/>

        </Switch>
    );

}

export default Router;