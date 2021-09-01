import {Route, Switch} from "react-router-dom";
import React from "react";
import Home from "../home/index.js";
import Inventory from "../inventory/index.js";
import Login from "../login/index.js"
import CreateUser from "../createUser";


export default class Router extends React.Component {
    render() {
        return (
            <Switch>
                <Route exact path='/' component={Home}/>
                <Route exact path='/about' component={Inventory}/>
                <Route exact path='/contact' component={Login}/>
                <Route exact path='/signup' component={CreateUser}></Route>

            </Switch>
        );
    }
}