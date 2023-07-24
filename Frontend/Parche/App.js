import { NavigationContainer } from '@react-navigation/native';
import "react-native-gesture-handler"
import { SafeAreaView,StyleSheet,StatusBar} from 'react-native';
import Navigation from "./src/navigation/NavigationStack";
import Parches from './src/screen/Parches';

export default function App() {
  return (
    <NavigationContainer> 
      <Navigation/>
    </NavigationContainer>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
