import React from 'react'
import { useFormik } from 'formik';
import { Button,Col,Row,Form,Modal, FormControl } from 'react-bootstrap';
import * as yup from 'yup';
import AxiosClient from '../../../shared/plugins/axios';
import FeatherIcon from 'feather-icons-react/build/FeatherIcon';
import Alert,{confirmMsj,confirmTitle,errorMsj,errorTitle,successMsj,successTitle} from '../../../shared/plugins/alerts'




export const ProductForm = ({isOpen, setProducts, onClose}) => {
    const [subCategories, setSubCategories] = React.useState([]);


    React.useEffect(() => {
        const getSubCategories = async () => {
            try {
                const response = await AxiosClient({
                    method:"GET",
                    url:"/subcategory/"
                });
                if(!response.error){
                    setSubCategories(response.data);
                }
            } catch (error) {
                console.log(error);
            }
        }
        getSubCategories();
    },[]);

    const form = useFormik({
        initialValues:{
            name:"",
            description:"",
            price:"",
            brand:"",
            cuantity:"",
            subCategoryid:"",
            status:true
        },
        validationSchema: yup.object().shape({
            name: yup.string().required("Campo obligatorio").min(4,"Minimo 4 caracteres"),
            description: yup.string().required("Campo obligatorio").min(4,"Minimo 4 caracteres"),
            price: yup.number().required("Campo obligatorio").min(1,"Minimo 1 caracteres"),
            brand: yup.string().required("Campo obligatorio").min(4,"Minimo 4 caracteres"),
            cuantity: yup.number().required("Campo obligatorio").min(1,"Minimo 1 caracteres"),
        }),
        onSubmit:async (values) => {
        }
    });

    const handleClose = () => {
        onClose();
        form.resetForm();
    }




    return (
        <Modal show={isOpen} onHide={handleClose} size="lg">
            <Modal.Header closeButton>
                <Modal.Title>Crear Producto</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={form.handleSubmit}>
                    <Row>
                        <Col md={6}>
                            <Form.Group>
                                <Form.Label>Nombre</Form.Label>
                                <FormControl
                                    type="text"
                                    name="name"
                                    value={form.values.name}
                                    onChange={form.handleChange}
                                    isInvalid={form.errors.name}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {form.errors.name}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                        <Col md={6}>
                            <Form.Group>
                                <Form.Label>Descripción</Form.Label>
                                <FormControl
                                    type="text"
                                    name="description"
                                    value={form.values.description}
                                    onChange={form.handleChange}
                                    isInvalid={form.errors.description}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {form.errors.description}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <Col md={6}>
                            <Form.Group>
                                <Form.Label>Precio</Form.Label>
                                <FormControl
                                    type="number"
                                    name="price"
                                    value={form.values.price}
                                    onChange={form.handleChange}
                                    isInvalid={form.errors.price}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {form.errors.price}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                        <Col md={6}>
                            <Form.Group>
                                <Form.Label>Marca</Form.Label>
                                <FormControl
                                    type="text"
                                    name="brand"
                                    value={form.values.brand}
                                    onChange={form.handleChange}
                                    isInvalid={form.errors.brand}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {form.errors.brand}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <Col md={6}>
                            <Form.Group>
                                <Form.Label>Cantidad</Form.Label>
                                <FormControl

                                    type="number"
                                    name="cuantity"
                                    value={form.values.cuantity}
                                    onChange={form.handleChange}
                                    isInvalid={form.errors.cuantity}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {form.errors.cuantity}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                        <Col md={6}>
                            <Form.Group>
                                <Form.Label>Subcategoria</Form.Label>
                                <FormControl
                                    as="select"
                                    name="subCategoryid"
                                    value={form.values.subCategoryid}
                                    onChange={form.handleChange}
                                    isInvalid={form.errors.subCategoryid}
                                >
                                    <option value="">Seleccione una subcategoria</option>
                                    {subCategories.map((subCategory) => (
                                        <option key={subCategory.id} value={subCategory.id}>{subCategory.name}</option>
                                    ))}
                                </FormControl>
                                <Form.Control.Feedback type="invalid">
                                    {form.errors.subCategoryid}
                                </Form.Control.Feedback>
                            </Form.Group>

                        </Col>
                    </Row>
                    <Row>
                        <Col md={6}>
                            <Form.Group>
                                <Form.Label>Estado</Form.Label>
                                <FormControl
                                    as="select"
                                    name="status"
                                    value={form.values.status}
                                    onChange={form.handleChange}
                                    isInvalid={form.errors.status}
                                >
                                    <option value="">Seleccione un estado</option>
                                    <option value="true">Activo</option>
                                    <option value="false">Inactivo</option>
                                </FormControl>
                                <Form.Control.Feedback type="invalid">
                                    {form.errors.status}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <Col md={12}>
                            <Button variant="primary" type="submit" className="float-right">
                                <FeatherIcon icon="save" size="16" className="mr-2" />
                                Guardar
                            </Button>
                        </Col>
                    </Row>
                </Form>
            </Modal.Body>
        </Modal>

    );
};

export default ProductForm;