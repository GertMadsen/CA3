import React, { Component } from 'react';
import {FlatList, Alert, ActivityIndicator, Button, Text, StyleSheet, View, TouchableOpacity, Image } from 'react-native';
import { StackNavigator } from 'react-navigation';
import ShowDetails from './showdetails';

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
            <View style={{flex: 1, paddingTop:20}}>
              <FlatList
                data={this.state.datasource}
                renderItem={({item,index})=>{
                    return(
                        <FlatListItem item={item} index={index}/>
                    );
                }} 
            
              />
            </View>
        );
    }
}

class FlatListItem extends Component{
    
    _onPressButton(){
        Alert.alert('You tapped the button!')
    }

    
    
    render(){
        // const {navigate} = this.props.navigation;
        return(
                // <TouchableOpacity onPress={() => navigate('ShowDetails', {regno: 'LAA0001'})}>
                <TouchableOpacity onPress={this._onPressButton}>
                <View style={{
                    flex: 1,
                    flexDirection: 'row',
                    backgroundColor: this.props.index % 2 == 0 ? 'black': 'steelblue'
                }}>
                <Image source={{uri: this.props.item.picture}}
                   style={{width: 120, height: 100, margin: 5}}/>
                <View style={{
                   flex: 1,
                   flexDirection: 'column',
                }}>
                    <Text style={styles.flatListItem}>{this.props.item.make} {this.props.item.model}</Text>
                    <Text style={styles.flatListItem}>{this.props.item.category}, {this.props.item.location}, {this.props.item.priceperday} kr</Text>
                </View>
                </View>
                </TouchableOpacity>
           
        );
    }
}

const RouteStack = StackNavigator({
    ShowDetails: { screen: ShowDetails },
  })

const styles = StyleSheet.create({
    flatListItem: {
        color: 'white',
        padding: 10,
        fontSize: 16,
    }
})

