import React from 'react';
import axios from "axios";
import Cookies from "js-cookie";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import "./Logout.css"

const csrfToken = Cookies.get('XSRF-TOKEN');

const Logout = () => {

    {/* TODO Make sure username is getting removed from App on logout*/}

    const handleSubmit = () => {
        axios({
            method: 'GET',
            url: "logout",
            headers: {
                Authorization: 'Basic ' + window.btoa('caseStudyUser:Hamster5Lobster9Lightbulb'),
                'X-XSRF-TOKEN': csrfToken
            }
        })
    };

    return (
        <div className="Logout">
            <Form onSubmit={handleSubmit}>
                <span className ="form-header"> Are you sure you want to sign out? </span>
                <Button block size="lg" type="submit"> Log Out </Button>
            </Form>
        </div>
    );
}

export default Logout;

