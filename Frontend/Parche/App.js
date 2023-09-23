import { NavigationContainer } from '@react-navigation/native';
import "react-native-gesture-handler"
import { AuthProvider } from './src/Context/AuthContext';
import { Provider as PaperProvider } from 'react-native-paper';
import { SafeAreaView,StyleSheet,StatusBar} from 'react-native';
import Navigation from "./src/navigation/NavigationStack";

export default function App() {
  return (
    <PaperProvider>
    <AuthProvider>
      <NavigationContainer> 
        <Navigation />
      </NavigationContainer>
    </AuthProvider>
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
