import React, { useState,useEffect } from 'react'
import { Badge, Card, Col, Row } from 'react-bootstrap'
import Alert, {
    confirmMsj, confirmTitle, errorMsj, errorTitle, successMsj, successTitle
} from '../../shared/plugins/alerts'
import AxiosClient from './../../shared/plugins/axios';
import Loading from '../../shared/components/Loading';
import { FilterComponent } from '../../shared/components/FilterComponent';
import { ButtonCircle } from '../../shared/components/ButtonCircle';
import DataTable from 'react-data-table-component';
import {SubCategoryForm} from './components/SubCategoryForm';


const options ={
    rwsPerPageText: 'Registros por pagina',
    rangeSeparatorText: 'de'
}


const SubCategoryScreens = () => {
    const [subCategories, setsubCategories] = useState([])
    const [selectedSubCategory, setselectedSubCategory] = useState({})
    const [isLoading, setisLoading] = useState(false)
    // const [isEditing, setisEditing] = useState(false)
    const [filterText, setfilterText] = useState("")
    const [isOpen, setIsOpen] = useState(false)
    const filteredSubCategories = subCategories.filter(
        subCategories=> subCategories.name && subCategories.name.toLowerCase().includes(filterText.toLocaleLowerCase())
    )

    const getSubCategories = async()=>{
        try {
            setisLoading(true)
            const data = await AxiosClient({url:"/subcategory/"})
            if(!data.error){
                setsubCategories(data.data)
                setisLoading(false)
            }
        } catch (error) {
            setisLoading(false)
            
        }
    }
    useEffect(()=>{
        getSubCategories();
    },[])

    const enableOrDisable = (row) => {
        console.log('Row', row);
        Alert.fire({
            title: confirmTitle,
            text: confirmMsj,
            icon: 'warning',
            confirmButtonColor: '#009574',
            confirmButtonText: 'Aceptar',
            cancelButtonColor: '#DD6B55',
            cancelButtonText: 'Cancelar',
            reverseButtons: true,
            backdrop: true,
            showCancelButton: true,
            showLoaderOnConfirm: true,
            allowOutsideClick: () => !Alert.isLoading,
            preConfirm: async () => {
                row.status = !row.status
                console.log('Row', row);
                try {
                    const response = await AxiosClient({
                        method: 'PATCH',
                        url: '/subcategory/',
                        data: JSON.stringify(row),
                    })
                    if (!response.error) {
                        Alert.fire({
                            title: successTitle,
                            text: successMsj,
                            icon: 'success',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: 'Aceptar'
                        })
                    }
                    console.log('response', response);
                    return response
                } catch (error) {
                    Alert.fire({
                        title: errorTitle,
                        text: errorMsj,
                        icon: 'error',
                        confirmButtonColor: '#3085d6',
                        confirmButtonText: 'Aceptar'
                    })
                } finally {
                    getSubCategories()
                }
            }
        })
    }

    const headerComponent = React.useMemo(()=>{
        const handleClear = () =>{
            if(filterText) setfilterText('');
        };
        return(
            <FilterComponent
                onFilter={(e)=> setfilterText(e.target.value)}
                onClear={handleClear}
                filterText={filterText}
            />
        )
    },[filterText]);

    const columns = React.useMemo(()=> [
        {
            name:"#",
            cell: (row, index) => <div>{index+1}</div>,
            sortable:true,
        },
        {
            name:"Categoria",
            cell: (row)=><div>{row.category.name}</div>,
            sortable:true,
            selector: (row) => row.category.name,
        },{
            name:"SubCatgoria",
            cell: (row)=><div>{row.name}</div>,
            sortable:true,
            selector: (row) => row.name,
        },{
            name:"Estado",
            cell: (row) => row.status ? (<Badge bg="success">Activo</Badge>) : (<Badge bg="danger">Inactivo</Badge>),
            sortable:true,
            selector: (row) => row.status
        },{
            name:"Acciones",
            cell:(row)=> <>
                <ButtonCircle
                    icon='edit'
                    type={"btn btn-outline-warning btn-circle"}
                    size={16}
                    onClick={()=> {
                        // setisEditing(true);
                        setselectedSubCategory(row);
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
            
        }
    ]);





  return <Card>
  <Card.Header>
      <Row>
          <Col>Categorias</Col>
          <Col className='text-end'>
              <ButtonCircle 
                  type={'btn btn-outline-success'}
                    onClick={()=>setIsOpen(true)}
                  icon='plus'
                  size={16}
              />
              {/* <EdtCategoryForm isOpen={ isOpen} onclose = {()=> setIsOpen(false)} setsubCategories={setsubCategories} />  */}
              <SubCategoryForm isOpen={ isOpen} onclose = {()=> setIsOpen(false)}  setsubCategories={setsubCategories} subCategories={selectedSubCategory} />
          </Col>
      </Row>
  </Card.Header>
  <Card.Body>
      <DataTable
          columns={columns}
          data={filteredSubCategories}
          progressPending={isLoading}
          progressComponent={<Loading/>}
          noDataComponent={'Sin registros'}
          pagination
          paginationComponentOptions={options}
          subHeader
          subHeaderComponent={headerComponent}
          persistTableHead
          striped={true}
          highlightOnHover={true}

      />
  </Card.Body>
</Card>
}

export default SubCategoryScreens