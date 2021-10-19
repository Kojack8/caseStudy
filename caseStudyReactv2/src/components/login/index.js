import React, {useEffect, useState} from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import "./Login.css";
import axios from "axios";
import Cookies from "js-cookie";
import {useHistory} from "react-router-dom";

const baseURL = "login"
const csrfToken=  Cookies.get('XSRF-TOKEN');

const Login = (props) => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(false);
    const [username, setUsername] = useState("");
    const history = useHistory();

    useEffect(() => {
        goBack();
    })

    const goBack = () => {
        props.callBack(username);
    }

    const validateForm = () => {
        return email.length > 0 && password.length > 0;
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        axios({
                method: 'POST',
                url: `${baseURL}`,
                params: {
                  email: email,
                  password: password
                },
                headers: {
                    /*Authorization: 'Basic ' + window.btoa('caseStudyUser:Hamster5Lobster9Lightbulb'),*/
                    'X-XSRF-TOKEN': csrfToken
                }
            }
        ).then((response) => {
            setError(false)

            axios({
                method: 'GET',
                url: "user/name",
                headers: {
                    /*Authorization: 'Basic ' + window.btoa('caseStudyUser:Hamster5Lobster9Lightbulb'),*/
                    'X-XSRF-TOKEN': csrfToken
                }
            }).then((response) => {
                setUsername(response.data)
                history.push("/");
                window.location.reload(false);
            }).catch(err => {console.log(err)});
        })
        .catch(err => {
            setError(true);
        })

    }




    return (
        <div className="Login">
            <Form onSubmit={handleSubmit}>
                {error ? <span className="form-error"> Invalid Credentials </span> : null}
                <Form.Group size="lg" controlId="email">
                    <Form.Label>Email</Form.Label>
                    <Form.Control
                        autoFocus
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </Form.Group>
                <Form.Group size="lg" controlId="password">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </Form.Group>
                <Button block size="lg" type="submit" disabled={!validateForm()}>
                    Login
                </Button>
            </Form>
        </div>
    );
}

export default Login;