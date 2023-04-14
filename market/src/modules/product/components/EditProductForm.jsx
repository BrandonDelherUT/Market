import React from 'react'
import { useFormik } from 'formik';
import { Button,Col,Row,Form,Modal, FormControl } from 'react-bootstrap';
import * as yup from 'yup';
import AxiosClient from '../../../shared/plugins/axios';
import FeatherIcon from 'feather-icons-react'
import Alert,{confirmMsj,confirmTitle,errorMsj,errorTitle,successMsj,successTitle} from '../../../shared/plugins/alerts'




export const EditProductForm = ({isOpen, setProducts, onClose}) => {

    const [product, setProduct] = React.useState({});


    const form = useFormik({
        initialValues:{
            id:0,
            name:"",
            description:"",
            price:"",
        },
        validationSchema: yup.object().shape({
            
        }),
        onSubmit:async (values) => {
            Alert.fire({
                title: confirmTitle,
                text:confirmMsj,
                icon:"warning",
                confirmButtonColor:"#009574",
                confirmButtonText:"Aceptar",
                cancelButtonColor:"#DD6B55",
                cancelButtonText:"Cancelar",
                reverseButtons:true,
                backdrop:true,
                showCancelButton:true,
                showLoaderOnConfirm:true,
                allowOutsideClick: () => !Alert.isLoading,
                preConfirm: async () =>{
                }
            }).then((result)=>{
                if(result.isConfirmed){
                    handleClose();
                }
            }
            );
        }

    });

    const handleClose = () => {
        onClose();
        form.resetForm();
    }

    React.useMemo (() => {
        
    },[isOpen]);



    return (
        <Modal show={isOpen} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Editar Producto</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={form.handleSubmit}>
                    <Form.Group as={Row} className="mb-3" controlId="formHorizontalName">
                        <Form.Label column sm={2}>
                            Nombre
                        </Form.Label>
                        <Col sm={10}>
                            <Form.Control type="text" placeholder="Nombre" name="name" value={form.values.name} onChange={form.handleChange} isInvalid={form.errors.name}/>
                            <Form.Control.Feedback type="invalid">{form.errors.name}</Form.Control.Feedback>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3" controlId="formHorizontalDescription">
                        <Form.Label column sm={2}>
                            Descripcion
                        </Form.Label>
                        <Col sm={10}>
                            <Form.Control type="text" placeholder="Descripcion" name="description" value={form.values.description} onChange={form.handleChange} isInvalid={form.errors.description}/>
                            <Form.Control.Feedback type="invalid">{form.errors.description}</Form.Control.Feedback>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3" controlId="formHorizontalPrice">
                        <Form.Label column sm={2}>
                            Precio
                        </Form.Label>
                        <Col sm={10}>
                            <Form.Control type="number" placeholder="Precio" name="price" value={form.values.price} onChange={form.handleChange} isInvalid={form.errors.price}/>
                            <Form.Control.Feedback type="invalid">{form.errors.price}</Form.Control.Feedback>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3">
                        <Col sm={{ span: 10, offset: 2 }}>
                            <Button type="submit" variant="primary">Guardar</Button>
                        </Col>
                    </Form.Group>
                </Form>
            </Modal.Body>
        </Modal>

    );
};

export default EditProductForm;