import React, {useEffect} from "react";
import {
  StyleSheet,
  View,
  Text,
  Image,
  TouchableWithoutFeedback,
} from "react-native";
import { capitalize } from "lodash";
import moment from 'moment';
import 'moment/locale/es'; // Importa la localización en español
import { useNavigation } from "@react-navigation/native";

export default function parchecard(props){

    moment.locale('es');

    const {parche} = props;
    const navigation = useNavigation();

    const goToParche=()=>{
        console.log("ir a parche")
        navigation.navigate("parchesInfo",  { id: parche.id } );
        };

            useEffect(()=>{
                console.log(parche)
            },[])
    

    return ( 
        <TouchableWithoutFeedback onPress={goToParche}>
             <View style={styles.card}>
                <View style={styles.spacing}>
                    <View style={styles.bgStyles}>
                        <Text style={styles.number}>#{parche.id}</Text>
                        <Text style={styles.name}>{capitalize(parche.nombre)}</Text>
                        <Text style={styles.dateText}>
                            {moment(parche.fechaInicio).format('ddd D MMM').toLowerCase()} -{' '}
                            {moment(parche.fechaFin).format('ddd D MMM').toLowerCase()}
                        </Text>
                    </View>
                </View>
                
            </View>
            
        </TouchableWithoutFeedback>
           

    );
}

const styles=StyleSheet.create({
    card:{
        flex:2,
        height:130,
    },
    spacing:{
        flex:1,
        padding:5,
    },
    bgStyles:{
        flex:1,
        borderRadius:10,
        padding:10,
        backgroundColor : "grey",
    },
    number:{
        position:"absolute",
        right:5,
        top:5,
        color:"#fff",
        fontSize:11,
    },
    name: {
        color: "#fff",
        fontWeight: "bold",
        fontSize: 25, // Aumenta el tamaño del texto
        marginTop: 10, // Espacio superior
      },
    image:{
        position:"absolute",
        bottom:2,
        left:1,
        width:90,
        height:90,
    },
});