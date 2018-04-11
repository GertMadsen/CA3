import React from 'react';
import { Text, View } from 'react-native';

export default class StarWars extends React.Component {
    static navigationOptions = {title: "Star wars"}
    render(){
        return(
            <View>
                <Text>Star wars!</Text>
            </View>
        );
    }
}