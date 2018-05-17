import React, { Component } from 'react';
import {FlatList, ActivityIndicator, Button, Text, StyleSheet, View, TouchableOpacity } from 'react-native';

const URL = require("./package.json").serverURL;

export default class ShowCars extends Component {
    static navigationOptions = { title: "Show Cars" }

    getData() {
        
        return (
            fetch(URL + "/api/cars")
                .then((response) => response.json())
                .then((responseJson) => {
                    this.setState({
                        isLoading: false,
                        // car: responseJson.cars[0].regno,
                        datasource: responseJson.cars,
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
        return (
            // <View style={styles.container}>
            //     <Text style={styles.texting}>A car:</Text>
            //     <Text style={styles.texting}>Regno: {this.state.car}</Text>
            // </View>

            
            <View style={{flex: 1, paddingTop:20}}>
              <FlatList
                data={this.state.datasource}
                renderItem={({item}) => <Text>{item.make} {item.model} {item.category} {item.location} {item.priceperday}</Text>}
                keyExtractor={(item, index) => index}
              />
            </View>
        );
    }
}


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