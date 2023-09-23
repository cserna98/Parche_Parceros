    import React, { useState, useEffect } from 'react';
    import { SafeAreaView, View, Text, Button, StyleSheet,StatusBar,ScrollView } from 'react-native';
    import { getParcheById } from '../api/ParcheApi';
    import { useNavigation } from '@react-navigation/native';
    import { Menu, IconButton } from 'react-native-paper';
    import moment from 'moment';
    import StatusBarMargin from '../components/StatusBarMargin';
    import AsistenteList from '../components/AsistenteList';
    import { getAsistentesByParcheId } from '../api/AsistenteApi';
    import { deleteParche } from '../api/ParcheApi';
    import AttributeRow from '../components/AttributeRow';
    import { useAuth } from '../Context/AuthContext';
   



    function ParchesInfo(props) {
    const [parche, setParche] = useState(null);
    const [asistentes, setAsistentes] = useState([]);
    const [menuVisible, setMenuVisible] = useState(false);
    const [parcheId, setParcheId] = useState(null);
    const[startDay,setStartDay]= useState(null);
    const[endDay,setEndDay]= useState(null);
    const { token, setAuthToken } = useAuth();


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
        navigate.goBack()
        deleteParche(parcheId)
    };

    const getAsistentes = async (id) => {
        try {
        const response = await getAsistentesByParcheId(id, token);
        setAsistentes(response);
        } catch (error) {
        console.log('estoy dando error asistente');
        }
    };

    const fetchData = async (id) => {
        try {
        const response = await getParcheById(id,token);
        setParche(response);
        console.log("response parche")
        console.log(response);
        } catch (error) {
        console.log('no funcione me devuelvo yeiii');
        }
    };

    
 

    useEffect(() => {
        if (params) {
        setParcheId(params.id)
        console.log(params);
        fetchData(params.id);
        getAsistentes(params.id);
        }else{
            fetchData(parcheId);
            getAsistentes(parcheId)
        }

    }, []);

    useEffect(()=>{
      const startday= moment(parche?.fechaInicio);
      setStartDay(startday.format('dddd'))
      const endday= moment(parche?.fechaFin);
      setEndDay(endday.format('dddd'))
    },[parche])  


    useEffect(() => {
        fetchData(parcheId);
        getAsistentes(parcheId);
    }, [parcheId]);



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

        {/* containerAtributerow ocupa el 30% */}
        <View style={[styles.containerAtributerow, { flex: 3 }]}>
          {/* Renderiza las filas de atributos */}
          {AttributeRow('Nombre', parche?.nombre)}
          {AttributeRow('Fecha Inicio', `${startDay} ${parche?.fechaInicio?.split('T')[0]}`)}
          {AttributeRow('Fecha Fin', `${endDay} ${parche?.fechaFin?.split('T')[0]}`)}
          {AttributeRow('Costo', parche?.gastoTotal)}
        </View>

        {/* AsistenteList ocupa el 40% */}
          <AsistenteList
          
          style={[styles.AsistenteList, { flex: 1 }]}
            asistentes={asistentes}
            handleDeleteAsistente={handleDeleteAsistente}
          />
        {/* buttonContainer ocupa el 20% */}
        <View style={[styles.buttonContainer, { flex: 2 }]}>
          <Button title="+ Persona" onPress={goToAddPersona} style={styles.button} />
          <Button title="Add Item" onPress={goToAddItem} style={styles.button} />
          <Button title="Items List" onPress={goToItems} style={styles.button} />
        </View>
      </SafeAreaView>
    </StatusBarMargin>
  );
}
        
const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: 'white',
      padding: 20,
    },
    titleContainer: {
      flexDirection: 'row',
      justifyContent: 'space-between',
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
      justifyContent: 'space-between',
      marginTop: 10,
    },
    button: {
      backgroundColor: 'white',
      marginTop: 10,
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
  containerAtributerow: {
    flex: 3,
  },
  asistenteList: {
    flex: 4,
  },
});

export default ParchesInfo;