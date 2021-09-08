import React, {useState, useMemo, useEffect} from 'react'
import Select from 'react-select'
import countryList from 'react-select-country-list'

const CountrySelector = (props) =>{
    const [country, setCountry] = useState('')
    const options = useMemo(() => countryList().getData(), [])


    useEffect(() => {
        goBack();
    })

    {/* Callback method used with parent class */}
    const goBack = () => {
        props.callBack(country);
    }

    const changeHandler = value => {
        setCountry(value.label)

    }

    return <Select options={options} country={country} onChange={changeHandler} />
}

export default CountrySelector