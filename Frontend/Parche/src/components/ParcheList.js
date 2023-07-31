import React, { useEffect } from "react";
import { StyleSheet,  FlatList,ActivityIndicator,Text } from "react-native";
import Parchecard from "./ParcheCard";

export default function ParcheList(props) {

    const {parches} = props;
    
    useEffect(()=>{
        console.log(parches)
    },[])

    return ( 
        <FlatList
        data= {parches}
        keyExtractor = {(parche) => String(parche.id)}
        renderItem= {({item})=> <Parchecard parche={item}/>}
        />
        
    );
};

