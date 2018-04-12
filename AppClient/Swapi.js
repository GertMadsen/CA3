import React, { Component } from 'react';
import { Button, Text, StyleSheet, View, TouchableOpacity } from 'react-native';

const URL = require("./package.json").serverURL;

export default class Swapi extends Component {
    static navigationOptions = { title: "Star wars" }

    getRandomNumber() {
        return Math.floor(Math.random() * 88) + 1;
    }

    getData() {
        var randomNumber = this.getRandomNumber();
        return (
            fetch(URL + "/api/info/people/" + randomNumber)
                .then((response) => response.json())
                .then((responseJson) => {
                    this.setState({
                        isLoading: false,
                        datasource: responseJson.name,
                    }, function () {

                    });
                })
                .catch((error) => {
                    console.error(error)
                })
        );
    }

    _onPressButton() {
        this.getData();
    }

    constructor(props) {
        super(props);
        this.state = { isLoading: true }

    }

    componentDidMount() {
        this.getData();
    }

    render() {
        return (
            <View style={styles.container}>
                <Text style={styles.texting}>A Star Wars character: </Text>
                <Text style={styles.texting}>{this.state.datasource}</Text>
                <Touchable onPress={this._onPressButton.bind(this)} title="Get another character" />
            </View>
        );
    }
}

const Touchable = (props) => (
    <TouchableOpacity style={styles.button} onPress={props.onPress}>
        <Text style={styles.buttonText}>{props.title}</Text>
    </TouchableOpacity>
)

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        backgroundColor: 'black',
    },
    texting: {
        margin: 20,
        color: 'white',
        fontSize: 30,
    },
    button: {
        marginTop: 10,
        paddingTop: 7,
        paddingBottom: 7,
        marginLeft: 30,
        marginRight: 30,
        backgroundColor: '#999999',
        borderRadius: 10,
        borderWidth: 1,
        borderColor: '#4d4d4d',
        
    },
    buttonText: {
        textAlign: 'center',
        padding: 5,
        fontSize: 18,
        fontWeight: 'bold',
        color: 'white'
    }
});