package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService{
    public static final String API_V_01_VENDORS = "/api/v01/vendors";
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = VendorMapper.INSTANCE;

    }


    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendorMapper::vendorToVendorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        returnDTO.setVendorUrl(API_V_01_VENDORS);

        return returnDTO;
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendor.setId(id);


        return saveAndReturnDTO(vendor);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(savedVendor);

        returnDTO.setVendorUrl(API_V_01_VENDORS + savedVendor.getId());

        return  returnDTO;
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if (vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
            }

            VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
            returnDTO.setVendorUrl(API_V_01_VENDORS + vendor.getId());

            return vendorDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}
