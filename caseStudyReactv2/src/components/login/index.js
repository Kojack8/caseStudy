import React, {useState} from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import "./Login.css";
import axios from "axios";
import Cookies from "js-cookie";

const baseURL = "login"
const csrfToken=  Cookies.get('XSRF-TOKEN');

export default function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(false);

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
                    Authorization: 'Basic ' + window.btoa('caseStudyUser:Hamster5Lobster9Lightbulb'),
                    'X-XSRF-TOKEN': csrfToken
                }
            }
        ).then((response) => console.log(response))
        .catch(err => {
            console.log(err.message)
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