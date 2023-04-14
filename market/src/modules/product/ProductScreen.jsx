import React, { useState,useEffect } from 'react'
import { Card, Badge, Col, Row } from 'react-bootstrap';
import AxiosClient from '../../shared/plugins/axios'
import DataTable from 'react-data-table-component'
import {FilterComponent} from '../../shared/components/FilterComponent'
import Alert, {
    confirmMsj, confirmTitle, errorMsj, errorTitle, successMsj, successTitle
} from '../../shared/plugins/alerts'
import { ButtonCircle }     from '../../shared/components/ButtonCircle'
import ProductForm from './components/ProductForm';
import EditProductForm from './components/EditProductForm';


const options = {
    rwsPerPageText: 'Registros por pagina',
    rangeSeparatorText: 'de',
}

export const ProductScreen = () => {
    const [products, setProducts] = useState([])
    const [filterText, setFilterText] = useState("")
    const [isEditing, setIsEditing] = useState(false)
    const [selectProduct, setselectProduct] = useState({})
    const [isOpen, setIsOpen] = useState(false)

    const filterProducts = products.filter(
        category=> category.name && category.name.toLowerCase().
        includes(filterText.toLocaleLowerCase())
    );


    const getProducts = async() =>{
        try{
            const data = await AxiosClient({url:"/product/"});
            if(!data.error) {
                setProducts(data.data);
                console.log(data.data)
            };
        }catch(error){
            //sweet aler error
            console.log(error)
        }
    }

    useEffect(() => {
        getProducts();
    }, [] );

    const enableOrDisable = (row) => {
        console.log('Row', row);
        Alert.fire({
            title: confirmTitle,
            text: confirmMsj,
            icon: 'warning',
            confirmButtonColor: '#009574',
            confirmButtonText: 'Aceptar',
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            cancelButtonColor: '#d33',
        }).then((result) => {
            if (result.isConfirmed) {
                row.status = !row.status
                console.log('Row', row);
                AxiosClient({
                    url: `/product/`,
                    method: 'PATCH',
                    data: JSON.stringify(row),
                }).then((response) => {
                    if (!response.error) {
                        Alert.fire({
                            title: successTitle,
                            text: successMsj,
                            icon: 'success',
                            confirmButtonColor: '#009574',
                            confirmButtonText: 'Aceptar',
                        }).then(() => {
                            getProducts();
                        });
                    } else {
                        Alert.fire({
                            title: errorTitle,
                            text: errorMsj,
                            icon: 'error',
                            confirmButtonColor: '#009574',
                            confirmButtonText: 'Aceptar',
                        });
                    }
                });
            }
        });


    };


    const headerComponent = React.useMemo(()=>{
        const handleClear = () =>{
            if(filterText) setFilterText('');
        };
        return(
            <FilterComponent
                onFilter={(e)=> setFilterText(e.target.value)}
                onClear={handleClear}
                filterText={filterText}
            />
        )
    },[filterText]);


    const columns = [
        {
            name: 'Nombre',
            cell : row => <span>{row.name}</span>,
            sortable: true,
        },
        {
            name: 'Precio',
            cell : row => <span>{row.price}</span>,
            sortable: true,
        },
        {
            name: 'Stock',
            cell : row => <span>{row.cuantity}</span>,
            sortable: true,
        },
        {
            name:"Estado",
            cell: (row) => row.status ? (<Badge bg="success">Activo</Badge>) : (<Badge bg="danger">Inactivo</Badge>),
            sortable:true,
            selector: (row) => row.status
        },
        {
            name:"Acciones",
            cell:(row)=> <>
                <ButtonCircle
                    icon='edit'
                    type={"btn btn-outline-warning btn-circle"}
                    size={16}
                    onClick={()=> {
                        setIsEditing(true);
                        setselectProduct(row);
                    }}  
                />
                {
                    row.status ? (<ButtonCircle 
                        icon='trash-2' 
                        type={"btn btn-outline-danger btn-circle"} 
                        onClick={()=>{
                            enableOrDisable(row);
                        }} 
                        size={16} />) :
                    (<ButtonCircle   icon='pocket' type={"btn btn-outline-success btn-circle"} onClick={()=>{
                        enableOrDisable(row)
                    }} size={16} />)
                }
            </>
        },
    ];

    return (
        <Card>
            <Card.Header>
                <Row>
                    <Col>Productos</Col>
                    <Col className='text-end'>
                        <ButtonCircle 
                            type={'btn btn-outline-success'}
                            onClick={()=>setIsOpen(true)}
                            icon='plus'
                            size={16}
                        />
                        <ProductForm isOpen={ isOpen} onClose = {()=> setIsOpen(false)} setProducts={setProducts} /> 
                        <EditProductForm isOpen={isEditing} onClose={()=> setIsEditing(false)} setProducts={setProducts} product={selectProduct} />
                    </Col>
                </Row>
            </Card.Header>
            <Card.Body>
                <div className="table-responsive">
                    <DataTable
                        columns={columns}
                        data={filterProducts}
                        noDataComponent={'Sin registros'}
                        pagination
                        paginationComponentOptions={options}
                        subHeader
                        subHeaderComponent={headerComponent}
                        persistTableHead
                        striped={true}
                        highlightOnHover={true}
                        />
                </div>
            </Card.Body>


        </Card>
    );
};

export default ProductScreen;