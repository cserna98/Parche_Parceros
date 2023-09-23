import React, { useState, useEffect } from 'react';
import { SafeAreaView, Button, StyleSheet, View, StatusBar, Text } from 'react-native';
import { getParches } from '../api/ParcheApi';
import ParcheList from './ParcheList';
import { useNavigation } from '@react-navigation/native';
import { useFocusEffect } from '@react-navigation/native';
import StatusBarMargin from './StatusBarMargin';
import { useAuth } from '../Context/AuthContext';



    function Parches() {
    const [parches, setParches] = useState([]);
    const [isFocused, setIsFocused] = useState(false);
    const { token, setAuthToken } = useAuth();
    

    const navigation = useNavigation();

    const goToAddParche = () => {
        navigation.navigate('addParche', { parches: parches });
    };

    const loadParche = async () => {
        try {
        const response = await getParches(token);
        const parchesList = response.map((parche) => ({
            id: parche.id,
            nombre: parche.nombre,
            fechaInicio: parche.fechaInicio,
            fechaFin: parche.fechaFin,
            dias: parche.dias,
        }));
        setParches(parchesList);
        } catch (error) {
        console.error("error 1 " + error);
        }
    };

    useEffect(() => {
        console.log(parches)
        if (isFocused) {
            loadParche();
        }
    }, [isFocused]);

    useFocusEffect(
        React.useCallback(() => {
            setIsFocused(true);
            return () => setIsFocused(false);
        }, [])
    );


    return (
        <StatusBarMargin>
          <SafeAreaView style={styles.container}>
            <Text style={styles.title}>Parches</Text>
            <View style={styles.contentContainer}>
              <ParcheList parches={parches} />
              <Button title="+ Parche" onPress={goToAddParche} style={styles.button} />
            </View>
          </SafeAreaView>
        </StatusBarMargin>
      );
      
    }

    const styles = StyleSheet.create({
        container: {
          flex: 1,
          backgroundColor :"white"
        },
        title: {
          fontSize: 24,
          fontWeight: 'bold',
          alignSelf: 'center', // Centra el título horizontalmente
          marginTop: 5, // Espacio superior
          marginBottom: 10, // Margen inferior
        },
        contentContainer: {
          flex: 1,
          flexGrow: 1, // Hace que el contenido ocupe todo el espacio disponible
        },
        button: {
          width: '50%',
          alignSelf: 'center',
          borderRadius: 20,
          marginVertical: 10,
        },
      });

export default Parches;