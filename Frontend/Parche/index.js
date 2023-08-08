import { AppRegistry } from 'react-native';
import App from './App'; // Importa el componente principal de tu aplicación
import { name as appName } from './app.json';

AppRegistry.registerComponent(appName, () => App);