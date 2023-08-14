import { NavigationContainer } from '@react-navigation/native';
import "react-native-gesture-handler"
import { Provider as PaperProvider } from 'react-native-paper';
import { SafeAreaView,StyleSheet,StatusBar} from 'react-native';
import Navigation from "./src/navigation/NavigationStack";
import Parches from './src/screen/Parches';

export default function App() {
  return (
    <PaperProvider>
      <NavigationContainer> 
        <Navigation />
    </NavigationContainer>
    </PaperProvider>
    
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
