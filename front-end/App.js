import React, { Component } from 'react';
import { Text, View, StyleSheet, FlatList, ActivityIndicator,
  Button, Image, KeyboardAvoidingView, TextInput, TouchableOpacity,
	ScrollView, AsyncStorage, Alert } from 'react-native';
import { createStackNavigator, createAppContainer } from 'react-navigation';
import NotifService from './NotifService';
import appConfig from './app.json';
import { Table, TableWrapper, Row, Rows, Col, Cols, Cell } from 'react-native-table-component';

class LoginScreen extends Component {

	static navigationOptions = {
		header: null
	}

	constructor(props) {
		super(props);
		this.state = {
			username: '',
			password: '',
		}
	}

	login = () => {
		fetch('http://10.0.0.97:8080/users', {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({
				username: this.state.username,
				password: this.state.password,
			})
		})

		.then((response) => response.json())
		.then((res) => {
			if (res.success === true) {
				this.props.navigation.navigate('Alerts');
			} else {
				alert(res.message);
			}
		})
		.done();
	}



	render() {
		return (
      <KeyboardAvoidingView behavior="position" style={{flex: 1}}>
				<View style={{marginTop: 45, alignItems: 'center', justifyContent: 'center'}}>
					<Image style={{width: 285, height: 70}} source={require('./assets/logo.png')} />
					<Text style={{color: '#083486', marginTop: 10, fontSize: 16, width: 210, textAlign: 'center'}}>Solutions for Market Surveillance and Compliance</Text>
				</View>
				<View style={{padding: 20, paddingTop: 260}}>
					<TextInput
						autoCapitalize="none"
						autoCorrect={false}
						keyboardType="email-address"
						onSubmitEditing={() => this.passwordInput.focus()}
						returnKeyType="next"
						placeholder="Username"
						onChangeText={(username) => this.setState({username})}
						placeholderTextColor='white'
						style={styles.input} />
					<TextInput
						autoCapitalize="none"
						ref={(input) => this.passwordInput = input}
						secureTextEntry={true}
						returnKeyType="go"
						placeholder="Password"
						onChangeText={(password) => this.setState({password})}
						placeholderTextColor='white'
						style={styles.input} />
					<TouchableOpacity style={styles.buttonContainer} onPress={this.login}>
						<Text style={styles.buttonText}>Login</Text>
					</TouchableOpacity>
				</View>
			</KeyboardAvoidingView>
		);
	}
}

class AlertsScreen extends Component {
	
	static navigationOptions = {
		title: 'Alerts',
		headerRight: <View />
	}
	
	constructor(props){
		super(props);
		this.state = {
			isLoading: true,
			dataSource: null
		}
	}

	componentDidMount() {
		this.refresh();
	}
	
	refresh = () => {
		return fetch('http://10.0.0.97:8080/alerts')
		
		.then ( (response) => response.json() )
		.then ( (responseJson) => {
			this.setState({
				isLoading: false,
				dataSource: responseJson,
			});
		})
		
		.catch((error) => {
			console.log(error)
		})
		.done();
	}

	render() {
		if(this.state.isLoading) {
			return (
				<View style = {styles.container}>
					<ActivityIndicator/>
				</View>
			)
		}
		
		return (
			<View style={{flex: 1}}>
			<TouchableOpacity style={{backgroundColor: '#083486', paddingVertical: 5}} onPress={this.refresh}>
				<Text style={{textAlign: 'center', color: 'white', fontWeight: '700'}}>Refresh</Text>
			</TouchableOpacity>
				<FlatList
					data={this.state.dataSource}
					keyExtractor={item => item.alertId}
					renderItem={({item}) =>
						<View style={styles.alertEntry}>
							<Text onPress={() => this.props.navigation.navigate('Details', {
										_name: item.name,
										_alertruleid: item.alertRuleId,
										_alertid: item.alertId,
										_obshortname: item.obShortName,
										_priority: item.priority,
										_description: item.description,
										_alertinfo: item.alertInfo
									})
								} style={{fontWeight: 'bold', marginLeft: 2}}>
								{item.name}{"\n"}
								{item.priority}{"\n"}
								________________________________________________________
							</Text>
						</View>
					}
				/>
			</View>
		);
	}
}

class DetailsScreen extends Component {
	
	static navigationOptions = {
		title: 'Details',
		headerRight: <View />
	}
	
	render() {
		const { navigation } = this.props;
		const _name = navigation.getParam('_name', 'defaultNameValue');
		const _alertruleid = navigation.getParam('_alertruleid', 'defaultAlertRuleIdValue');
		const _alertid = navigation.getParam('_alertid', 'defaultAlertIdValue');
		const _obshortname = navigation.getParam('_obshortname', 'defaultObShortNameValue');
		const _priority = navigation.getParam('_priority', 'defaultPriorityValue');
		const _description = navigation.getParam('_description', 'defaultDescriptionValue');
		const _alertinfo = navigation.getParam('_alertinfo', 'defaultAlertInfoValue');
		
		var AlertInfo = JSON.parse(_alertinfo);
		
		var dynamicTable = [];
		var count = 100;
		for (var i = 0; i < AlertInfo[4].length; i++) {
			dynamicTable.push(
				<View key = {i}>
					<Table borderStyle={{borderWidth: 1, borderColor: '#c8e1ff'}}>
						<Row data={[AlertInfo[4][i][3]]} style={{height: 30, backgroundColor: '#c8e1ff'}} textStyle={{textAlign: 'center'}}/>
					</Table>
				</View>
			)
			for (var j = 0; j < Object.keys(AlertInfo[4][i][1]).length; j++) {
				dynamicTable.push(
					<View key = {count}>
						<Table borderStyle={{borderWidth: 1, borderColor: '#c8e1ff'}}>
							<Row data={[[AlertInfo[4][i][1][j]], [AlertInfo[4][i][2][0][j]]]} flexArr={[1, 2]} textStyle={{marginLeft: 5}}/>
						</Table>
					</View>
				)
				count++;
			}
		}
		
		return (
			<ScrollView>
				<Table borderStyle={{borderWidth: 2, borderColor: '#c8e1ff'}}>
					<Rows data={[
						['Name', _name],
						['AlertruleID', _alertruleid],
						['AlertID', _alertid],
						['ObShortName', _obshortname],
						['Priority', _priority],
						['Description', _description]
					]} flexArr={[1, 2]} textStyle={{marginLeft: 5}}/>
				</Table>
				{dynamicTable}
			</ScrollView>
		);
	}
}

const RootStack = createStackNavigator(
	{
		Login: LoginScreen,
		Alerts: AlertsScreen,
		Details: DetailsScreen
	},
	{
		initialRouteName: 'Login',
		defaultNavigationOptions: {
			headerStyle: {
				backgroundColor: '#083486'
			},
			headerTintColor: 'white',
			headerTitleStyle: {
				textAlign: 'center',
				flex: 1
			}
		}
	}
);

type Props = {};

const AppContainer = createAppContainer(RootStack);

export default class App extends Component<Props> {

  constructor(props) {
    super(props);
    this.state = {
      senderId: appConfig.senderID
    };

    this.notif = new NotifService(this.onRegister.bind(this), this.onNotif.bind(this));
  }

  render() {
    return (
      <AppContainer />
    );
  }

  onRegister(token) {
    //Alert.alert("Registered !", JSON.stringify(token));
    console.log(token);
    this.setState({ registerToken: token.token, gcmRegistered: true });

    fetch('http://10.0.0.97:8080/token', {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({
        token: token
			})
		})
  }

  onNotif(notif) {
    console.log(notif);
    // Alert.alert(notif.title, notif.message);
  }

  handlePerm(perms) {
    Alert.alert("Permissions", JSON.stringify(perms));
  }
}


const styles = StyleSheet.create({
	container: {
		flex: 1,
		backgroundColor: '#fff',
		alignItems: 'center',
		justifyContent: 'center'
	},
	alertEntry: {
		flex: 1,
		flexDirection: 'row',
		justifyContent: 'space-between',
		alignItems: 'stretch'
	},
	input: {
		height: 40,
		backgroundColor: 'rgba(0,0,255,0.15)',
		marginBottom: 10,
		borderRadius: 25,
		fontSize: 16,
		paddingLeft: 20
	},
	buttonContainer: {
		backgroundColor: '#083486',
		paddingVertical: 15,
		borderRadius: 25
	},
	buttonText: {
		textAlign: 'center',
		color: 'white',
		fontWeight: '700',
		fontSize: 18
	}
});
