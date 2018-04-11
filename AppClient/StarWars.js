import React from 'react';
import { Text, View } from 'react-native';

// const URL = "https://swapi.co/api/people/1/?format=json";
// const URL = "http://restcountries.eu/rest/v1/alpha?codes=de"
const URL = "https://www.ramsbone.dk/CA3/api/info/people/4";

export default class StarWars extends React.Component {
    static navigationOptions = {title: "Star wars"}

    constructor(props){
        super(props);
        this.state={isLoading: true}
    }

    componentDidMount(){
        return(
            fetch(URL)
            .then((response) => response.json())
            .then((responseJson) =>{
                this.setState({
                    isLoading: false,
                    // datasource: responseJson[0].name,
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
            <View>
                <Text>Star wars!</Text>
                <Text>A character: {this.state.datasource}</Text>
            </View>
        );
    }
}