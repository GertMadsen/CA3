import React, { Component } from 'react';
import {Image, Button, Text, StyleSheet, View, TouchableOpacity, ScrollView } from 'react-native';

const URL = require("./package.json").serverURL;

export default class ShowDetails extends Component {
    static navigationOptions = { title: "Details" }
    getData() {
        
        return (
            fetch(URL + "/api/cars/LAA0001")
                .then((response) => response.json())
                .then((responseJson) => {
                    this.setState({
                        isLoading: false,
                        datasource: responseJson,
                    }, function () {

                    });
                })
                .catch((error) => {
                    console.error(error)
                })
        );
    }
    
    constructor(props) {
        super(props);
        this.state = { isLoading: true, datasource: [] }

    }

    componentDidMount() {
        this.getData();
    }

    render() {
        var car = this.state.datasource;
        let pic = {
            uri: car.picture};
        let logo = {
                uri: car.logo};
        let aircon = "" + car.aircondition;
        return (
            <View style={styles.container}> 
            
                <Image source={pic} style={{width: '100%', height: 200}} />
                <Text style={styles.showText}>Regno: {car.regno}</Text>
                <Text style={styles.showText}>Make: {car.make}</Text>
                <Text style={styles.showText}>Model: {car.model}</Text>
                <Text style={styles.showText}>Year: {car.year}</Text>
                <Text style={styles.showText}>Location: {car.location}</Text>
                <Text style={styles.showText}>Price per day: {car.priceperday}</Text>
                <Text style={styles.showText}>Seats: {car.seats}</Text>
                <Text style={styles.showText}>Doors: {car.doors}</Text>
                <Text style={styles.showText}>Gear: {car.gear}</Text>
                <Text style={styles.showText}>Aircondition: {aircon}</Text>
                <Text style={styles.showText}>Company: {car.company}</Text>
                <Image source={logo} style={{width: 50, height: 50, position: "absolute", bottom: 0, right: 0}} />
                
            </View>
        );
    }

}

const styles = StyleSheet.create({
    container: {
        backgroundColor: 'steelblue',
        padding: 10,
    },
    showText: {
        color: 'white',
        fontSize: 20,
    }

})