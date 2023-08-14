    import React, { useState, useEffect } from 'react';
    import { SafeAreaView, View, Text, Button, StyleSheet,StatusBar } from 'react-native';
    import { getParcheById } from '../api/ParcheApi';
    import { useNavigation } from '@react-navigation/native';
    import AsistenteList from '../components/AsistenteList';
    import { getAsistentesByParcheId } from '../api/AsistenteApi';
    import { Menu, IconButton } from 'react-native-paper';
    import StatusBarMargin from '../components/StatusBarMargin';


    function ParchesInfo(props) {
    const [parche, setParche] = useState(null);
    const [asistentes, setAsistentes] = useState([]);
    const [menuVisible, setMenuVisible] = useState(false);

    const navigate = useNavigation();

    const {
        navigation,
        route: { params },
    } = props;

    const goToAddPersona = () => {
        navigate.navigate('addAsistente', {
        idParche: params.id,
        asistentes: asistentes,
        });
    };

    const goToAddItem = () => {
        navigate.navigate('addItem', {
        idParche: params.id,
        nombreParche: parche.nombre,
        asistentes: asistentes,
        });
    };

    const goToItems = () => {
        navigate.navigate('Items', {
        idParche: params.id,
        asistentes: asistentes,
        });
    };

    const handleDeleteAsistente = () => {
        console.log('actualizar funcione');
        getAsistentes(params.idParche); // Llama nuevamente a getItems para actualizar la lista
    };

    const handleMenuToggle = () => {
        setMenuVisible(!menuVisible);
    };

    const handleDeleteParche = () => {
        // Lógica para eliminar el parche
    };

    const getAsistentes = async (id) => {
        try {
        const response = await getAsistentesByParcheId(id);
        setAsistentes(response);
        } catch (error) {
        console.log('estoy dando error');
        navigation.goBack();
        }
    };

    const fetchData = async (id) => {
        try {
        const response = await getParcheById(id);
        setParche(response);
        console.log('holi response ' + response);
        } catch (error) {
        console.log('no funcione me devuelvo yeiii');
        navigation.goBack();
        }
    };

    const renderAttributeRow = (attributeName, attributeValue) => {
        return (
            <View style={styles.attributeRow}>
                <Text style={styles.attribute}>{attributeName}:</Text>
                <Text style={styles.value}>{attributeValue}</Text>
            </View>
            );
        };

    useEffect(() => {
        if (params) {
        console.log(params);
        fetchData(params.id);
        getAsistentes(params.id);
        }
    }, [params]);



    return (

        <StatusBarMargin>
            <SafeAreaView style={styles.container}>
            <View style={styles.titleContainer}>
                <Text style={styles.title}>Parche</Text>
                <View style={styles.menuContainer}>
                <Menu
                    visible={menuVisible}
                    onDismiss={handleMenuToggle}
                    anchor={
                    <IconButton
                        icon="dots-vertical"
                        size={20}
                        onPress={handleMenuToggle}
                        style={styles.menuIcon}
                    />
                    }>
                    <Menu.Item onPress={handleDeleteParche} title="Borrar Parche" />
                </Menu>
                </View>
            </View>
        
            {/* Renderiza las filas de atributos */}
            {renderAttributeRow('Nombre', parche?.nombre)}
            {renderAttributeRow('Fecha Inicio', parche?.fechaInicio?.split('T')[0])}
            {renderAttributeRow('Fecha Fin', parche?.fechaFin?.split('T')[0])}
        
            <AsistenteList
                asistentes={asistentes}
                handleDeleteAsistente={handleDeleteAsistente}
            />
            <View style={styles.buttonContainer}>
            <Button
            title="+ Persona"
            onPress={goToAddPersona}
            style={styles.button}
            />
            <Button
            title="Add Item"
            onPress={goToAddItem}
            style={styles.button}
            />
            <Button
            title="Items List"
            onPress={goToItems}
            style={styles.button}
            />
            </View>
            </SafeAreaView>
        </StatusBarMargin>
        );
        }
        
        const styles = StyleSheet.create({

        container: {
            flex: 1,
            backgroundColor: "white",
            padding: 20,
        },

        titleContainer: {
            flexDirection: 'row',
            justifyContent: 'space-between', // Cambia "center" por "space-between"
            alignItems: 'center',
            marginBottom: 10,
            },
            title: {
            fontSize: 18,
            fontWeight: 'bold',
            color: 'black',
            },
            menuButton: {
            marginRight: -8,
            },
            buttonContainer: {
            flexDirection: 'row',
            justifyContent: 'space-between',
            marginVertical: 10,
            },
            roundedButton: {
            backgroundColor: 'grey',
            borderRadius: 20,
            paddingVertical: 10,
            paddingHorizontal: 20,
            },
            buttonText: {
            color: 'white',
            fontWeight: 'bold',
            },
            menuIcon: {
            color: 'black',
            },
            menuContainer: {
            position: 'absolute',
            top: 0,
            right: 0,
            },

            attributeRow: {
                flexDirection: 'row',
                justifyContent: 'space-between',
                marginBottom: 5,
            },
            attribute: {
                fontWeight: 'bold',
                fontSize: 18,
            },
            value: {
                fontSize: 18,
            },
        });
        
        export default ParchesInfo;
    
