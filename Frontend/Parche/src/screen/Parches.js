import React, { useState, useEffect } from 'react';
import { SafeAreaView, Button, StyleSheet, View, StatusBar, Text } from 'react-native';
import { ParchesApi } from '../api/ParcheApi';
import ParcheList from '../components/ParcheList';
import { useNavigation } from '@react-navigation/native';
import StatusBarMargin from '../components/StatusBarMargin';


    function Parches() {
    const [parches, setParches] = useState([]);

    const navigation = useNavigation();

    const goToAddParche = () => {
        navigation.navigate('addParche', { parches: parches });
    };

    const loadParche = async () => {
        try {
        const response = await ParchesApi();
        const parchesList = response.map((parche) => ({
            id: parche.id,
            nombre: parche.nombre,
            fecha: parche.fecha,
            dias: parche.dias,
        }));
        setParches([...parches, ...parchesList]);
        } catch (error) {
        console.error(error);
        }
    };

    useEffect(() => {
        loadParche();
    }, []);


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
        marginTop: Platform.OS === 'android' ? StatusBar.currentHeight : 0,
    },
    title: {
        fontSize: 24,
        fontWeight: 'bold',
        alignSelf: 'center', // Centra el título horizontalmente
        marginTop: 10, // Espacio superior
    },
    contentContainer: {
        flex: 1,
        justifyContent: 'center',
    },
    button: {
        width: '50%',
        alignSelf: 'center',
        borderRadius: 20,
        marginVertical: 10,
    },
    });

export default Parches;