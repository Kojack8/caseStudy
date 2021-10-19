import React, {useEffect, useState} from 'react';
import axios from "axios";
import Cookies from "js-cookie";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import "./Logout.css"
import {useHistory} from "react-router-dom";

const csrfToken = Cookies.get('XSRF-TOKEN');

const Logout = (props) => {
    const history = useHistory();
    const [username, setUsername] = useState(props.username);

    useEffect(() => {
        goBack();
    })

    const goBack = () => {
        props.callBack(username);
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        axios({
            method: 'POST',
            url: "user_logout",
            headers: {
                /* Authorization: 'Basic ' + window.btoa('caseStudyUser:Hamster5Lobster9Lightbulb'), */
                'X-XSRF-TOKEN': csrfToken
            }
        }).then(response => {
            setUsername("");
            history.push("/");
            window.location.reload(false);

        }).catch((err) => console.log(err));

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

