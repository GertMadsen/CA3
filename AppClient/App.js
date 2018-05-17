import React from 'react';
import { Text, View, Platform, TouchableOpacity, StyleSheet, Button, WebView, ScrollView } from 'react-native';
import { Constants, WebBrowser } from "expo";
import { StackNavigator } from 'react-navigation';
import ShowCars from './showcars';

const Touchable = (props) => (
  <TouchableOpacity style={styles.button} onPress={props.onPress}>
    <Text style={styles.buttonText}>{props.title}</Text>
  </TouchableOpacity>
  )

class HomeScreen extends React.Component{
  static navigationOptions = {title: 'Carmondo'};
  render(){
    const {navigate} = this.props.navigation;
    return(
      <View>
        <Text style={{ textAlign: "center", fontSize: 20 }}>Touch the button to see some cars</Text>
        <Touchable onPress={() => navigate('showcars')} title="Show Cars" />
      </View>
    );
  }
}

export default App = () => <RouteStack style={{ marginTop: Platform.OS === 'ios' ? 0 : Constants.statusBarHeight / 2 }} />

const RouteStack = StackNavigator({
  Home: { screen: HomeScreen},
  showcars: { screen: ShowCars },
})


const styles = StyleSheet.create({
  container: {
    flex: 1,
    color: '#fff',
    backgroundColor: 'steelblue',
    alignItems: 'center',
    justifyContent: 'center',
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
})
