import React, {useEffect, useState} from 'react';
import axios from "axios";
import Cookies from "js-cookie";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import styles from "./index.module.css"
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
        <div className={styles.wrapper}>
            <div className={styles.logout}>
                <span className={styles.title}> Log Out </span>
                <Form onSubmit={handleSubmit}>
                    <span className ={styles.formHeader}> Are you sure you want to sign out? </span>
                    <Button block size="lg" type="submit"> Log Out </Button>
                </Form>
            </div>
        </div>
    );
}

export default Logout;

