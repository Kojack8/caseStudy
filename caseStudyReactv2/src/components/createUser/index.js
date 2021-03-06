import React, {useState} from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import styles from "./index.module.css";
import axios from "axios";
import CountrySelector from "../../reuseable/countrySelector";
import StateSelector from "../../reuseable/stateSelector";
import Cookies from 'js-cookie';
import {useHistory} from "react-router-dom";


const baseURL = "users"
const csrfToken=  Cookies.get('XSRF-TOKEN');

const CreateUser = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [address1, setAddress1] = useState("");
    const [address2, setAddress2] = useState("");
    const [city, setCity] = useState("");
    const [state, setState] = useState("");
    const [country, setCountry] = useState("");
    const [zip, setZip] = useState("");
    const [phone, setPhone] = useState("");
    const history = useHistory();

    function validateForm() {

        return email.length > 0 && password.length > 0 && firstName.length > 0 && lastName.length > 0 &&
            address1.length > 0 && city.length > 0 && zip.length > 0 && country.length > 0 &&
            phone.length > 0;
    }

    /* Callback method for Country Select*/
    const countryCallBackData = (data) => {
        setCountry(data);
    }

    /* Callback method for State Select*/
    const stateCallBackData = (data) => {
        setState(data)
    }


    const handleSubmit = event => {
        event.preventDefault();
        const fullName = firstName + " " + lastName;
        const user = {
            email: email, password: password, fullName: fullName, address1: address1, address2: address2,
            city: city, state: state, country: country, zip: zip, phone: phone};

        axios({
                method: 'POST',
                url: `${baseURL}`,
                data: user,
                headers: {
                    //Authorization: 'Basic ' + window.btoa('caseStudyUser:Hamster5Lobster9Lightbulb'),
                    'X-XSRF-TOKEN': csrfToken
                }
            }
        ).then((response) => {
            history.push("/");
            console.log(response)})
            .catch(err => console.log(err));
    };



    return (
        <div className={styles.wrapper}>
            <Form onSubmit={handleSubmit}>
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
                <Form.Group size="lg" controlId="firstName">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control
                        type="firstName"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                    />
                </Form.Group>
                <Form.Group size="lg" controlId="lastName">
                    <Form.Label>Last Name</Form.Label>
                    <Form.Control
                        type="lastName"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                    />
                </Form.Group>
                <Form.Group size="lg" controlId="address1">
                    <Form.Label>Address Line 1</Form.Label>
                    <Form.Control
                        type="address1"
                        value={address1}
                        onChange={(e) => setAddress1(e.target.value)}
                    />
                </Form.Group>
                <Form.Group size="lg" controlId="address2">
                    <Form.Label>Address Line 2</Form.Label>
                    <Form.Control
                        type="address2"
                        value={address2}
                        onChange={(e) => setAddress2(e.target.value)}
                    />
                </Form.Group>
                <Form.Group size="lg" controlId="city">
                    <Form.Label>City</Form.Label>
                    <Form.Control
                        type="city"
                        value={city}
                        onChange={(e) => setCity(e.target.value)}
                    />
                </Form.Group>
                <Form.Group size="lg" controlId="state">
                    <Form.Label>State</Form.Label>
                    <StateSelector callBack={stateCallBackData}/>

                </Form.Group>
                <Form.Group size="lg" controlId="country">
                    <Form.Label>Country</Form.Label>
                    <CountrySelector callBack={countryCallBackData}/>

                </Form.Group>
                <Form.Group size="lg" controlId="zip">
                    <Form.Label>Zip</Form.Label>
                    <Form.Control
                        type="zip"
                        value={zip}
                        onChange={(e) => setZip(e.target.value)}
                    />
                </Form.Group>
                <Form.Group size="lg" controlId="phone">
                    <Form.Label>Phone</Form.Label>
                    <Form.Control
                        type="phone"
                        value={phone}
                        onChange={(e) => setPhone(e.target.value)}
                    />
                </Form.Group>

                <Button block size="lg" type="submit" disabled={!validateForm()}>
                    Create User
                </Button>
            </Form>
        </div>
    );
}

export default CreateUser;