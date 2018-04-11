import React, {Component} from 'react';
import {Button, Text, StyleSheet, View, TouchableOpacity} from 'react-native';

const URL = "https://www.ramsbone.dk/CA3/api/info/people/";



export default class Swapi extends Component{
    static navigationOptions = {title: "Star wars"}

    _onPressButton(){
        this.setState({dummy: 0});
    }

    constructor(props){
        super(props);
        var RandomNumber = Math.floor(Math.random()*88)+1;
        this.state={isLoading: true, rnd: RandomNumber}
        
    }

    componentDidMount(){
        return(
            fetch(URL+ this.state.rnd)
            .then((response) => response.json())
            .then((responseJson) =>{
                this.setState({
                    isLoading: false,
                    datasource: responseJson.name,
                }, function(){

                });
            })
            .catch((error) => {
                console.error(error)
            })
        );
    }

    render(){
        return(
            <View style={styles.container}>
                {/* <Text>Star wars!</Text> */}
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
        margin: 3,
        alignItems: 'center',
        backgroundColor: '#2196F3'
      },
      buttonText: {
        padding: 7,
        fontSize: 18,
        fontWeight: "bold",
        color: 'white'
      }
});