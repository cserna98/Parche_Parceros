import React from "react";
import {
  StyleSheet,
  View,
  Text,
  Image,
  TouchableWithoutFeedback,
} from "react-native";
import { capitalize } from "lodash";
import { useNavigation } from "@react-navigation/native";

export default function parchecard(props){

    const {parche} = props;
    const navigation = useNavigation();

    const goToParche=()=>{
        navigation.navigate("parchesInfo",  { id: parche.id } );
        };

    return ( 
        <TouchableWithoutFeedback onPress={goToParche}>
             <View style={styles.card}>
                <View style={styles.spacing}>
                    <View style={styles.bgStyles}>
                        <Text style={styles.number}>#{parche.id}</Text>
                        <Text style={styles.name}>{capitalize(parche.nombre)}</Text>
                        <Text style={styles.date}>#{parche.fecha}</Text>
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
    name:{
        color:"#fff",
        fontWeight:"bold",
        fontSize:15,
        top: 0,
        left:5,
    },
    image:{
        position:"absolute",
        bottom:2,
        left:1,
        width:90,
        height:90,
    },
});