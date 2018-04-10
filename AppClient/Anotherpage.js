import React from 'react';
import { Text, Image, StyleSheet, View } from 'react-native';

export default class Anotherpage extends React.Component{
    static navigationOptions = {title: "Another page"}
    render(){
        return(
            <View style={styles.container}>
                <Text style={styles.texting}>Hello World!</Text>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        // justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: 'blue',
    },
    texting: {
        margin: 20,
        color: 'white',
        fontSize: 40,
    }
});